#!/usr/bin/env python3
import math
import random
from dataclasses import dataclass
from typing import Tuple
from PIL import Image, ImageDraw

# Simple procedural "dragon" silhouette made of bezier curves and noise

@dataclass
class ColorPalette:
    background: Tuple[int, int, int]
    body: Tuple[int, int, int]
    wing: Tuple[int, int, int]
    eye: Tuple[int, int, int]
    flame: Tuple[int, int, int]


def cubic(p0, p1, p2, p3, t: float):
    u = 1 - t
    return (
        u**3 * p0[0] + 3 * u**2 * t * p1[0] + 3 * u * t**2 * p2[0] + t**3 * p3[0],
        u**3 * p0[1] + 3 * u**2 * t * p1[1] + 3 * u * t**2 * p2[1] + t**3 * p3[1],
    )


def jitter(pt, amt):
    return (pt[0] + random.uniform(-amt, amt), pt[1] + random.uniform(-amt, amt))


def draw_polyline(draw: ImageDraw.ImageDraw, points, color, width=3):
    for a, b in zip(points, points[1:]):
        draw.line([a, b], fill=color, width=width, joint="curve")


def bezier_points(p0, p1, p2, p3, steps):
    return [cubic(p0, p1, p2, p3, i / steps) for i in range(steps + 1)]


def draw_wing(draw, cx, cy, scale, palette: ColorPalette, flip=False):
    sign = -1 if flip else 1
    base = (cx, cy)
    tip = (cx + sign * 220 * scale, cy - 60 * scale)
    ctrl1 = (cx + sign * 120 * scale, cy - 140 * scale)
    ctrl2 = (cx + sign * 180 * scale, cy - 10 * scale)
    wing_edge = bezier_points(base, ctrl1, ctrl2, tip, 40)
    membrane = []
    for i in range(1, 6):
        t = i / 6
        px, py = cubic(base, ctrl1, ctrl2, tip, t)
        mx, my = (cx + sign * (40 + i * 20) * scale, cy + (40 + i * 10) * scale)
        membrane.extend([(px, py), (mx, my)])
    draw_polyline(draw, [jitter(p, 2 * scale) for p in wing_edge], palette.wing, width=int(6 * scale))
    for i in range(0, len(membrane), 2):
        draw.line([membrane[i], membrane[i + 1]], fill=palette.wing, width=int(3 * scale))


def draw_body(draw, cx, cy, scale, palette: ColorPalette):
    head_r = 26 * scale
    draw.ellipse([cx - head_r, cy - head_r, cx + head_r, cy + head_r], fill=palette.body)

    snout = bezier_points((cx + 10 * scale, cy), (cx + 50 * scale, cy - 10 * scale), (cx + 70 * scale, cy + 10 * scale), (cx + 90 * scale, cy), 20)
    draw_polyline(draw, snout, palette.body, width=int(12 * scale))

    eye_r = 6 * scale
    draw.ellipse([cx + 8 * scale - eye_r, cy - 6 * scale - eye_r, cx + 8 * scale + eye_r, cy - 6 * scale + eye_r], fill=palette.eye)

    # Neck and body spine
    neck = bezier_points((cx - 10 * scale, cy + 10 * scale), (cx - 40 * scale, cy + 40 * scale), (cx - 80 * scale, cy + 90 * scale), (cx - 120 * scale, cy + 120 * scale), 40)
    draw_polyline(draw, neck, palette.body, width=int(18 * scale))

    # Tail
    tail = bezier_points((cx - 120 * scale, cy + 120 * scale), (cx - 220 * scale, cy + 140 * scale), (cx - 280 * scale, cy + 200 * scale), (cx - 320 * scale, cy + 260 * scale), 60)
    draw_polyline(draw, tail, palette.body, width=int(12 * scale))

    # Spikes
    for i, (sx, sy) in enumerate(neck[::4]):
        h = (10 + (i % 3) * 4) * scale
        draw.polygon([(sx, sy - h), (sx + 8 * scale, sy), (sx - 8 * scale, sy)], fill=palette.body)


def draw_flame(draw, cx, cy, scale, palette: ColorPalette):
    # Stylized flame puff
    r1 = 10 * scale
    r2 = 18 * scale
    r3 = 26 * scale
    draw.ellipse([cx - r3, cy - r3, cx + r3, cy + r3], fill=palette.flame)
    draw.ellipse([cx - r2, cy - r2, cx + r2, cy + r2], fill=(255, 200, 80))
    draw.ellipse([cx - r1, cy - r1, cx + r1, cy + r1], fill=(255, 255, 255))


def generate(width=1024, height=768, seed=None, variant="emerald"):
    if seed is not None:
        random.seed(seed)

    palettes = {
        "emerald": ColorPalette(background=(10, 18, 28), body=(20, 180, 120), wing=(14, 120, 90), eye=(255, 240, 160), flame=(255, 120, 60)),
        "crimson": ColorPalette(background=(30, 12, 14), body=(200, 50, 60), wing=(140, 30, 40), eye=(255, 220, 180), flame=(255, 150, 70)),
        "amethyst": ColorPalette(background=(16, 12, 26), body=(140, 80, 220), wing=(90, 60, 170), eye=(250, 240, 255), flame=(255, 140, 90)),
    }

    palette = palettes.get(variant, palettes["emerald"])
    img = Image.new("RGB", (width, height), palette.background)
    draw = ImageDraw.Draw(img)

    cx = width * 0.55
    cy = height * 0.45
    scale = max(0.6, min(width, height) / 800)

    draw_wing(draw, cx - 40 * scale, cy + 30 * scale, scale, palette, flip=True)
    draw_wing(draw, cx - 10 * scale, cy + 20 * scale, scale * 0.9, palette, flip=False)
    draw_body(draw, cx, cy, scale, palette)
    draw_flame(draw, cx + 110 * scale, cy - 6 * scale, scale * 0.9, palette)

    # Foreground sparkles
    for _ in range(120):
        rx = random.uniform(0, width)
        ry = random.uniform(0, height)
        r = random.uniform(1, 2)
        draw.ellipse([rx - r, ry - r, rx + r, ry + r], fill=(255, 255, 255))

    return img


if __name__ == "__main__":
    import argparse
    parser = argparse.ArgumentParser(description="Generate a stylized dragon image")
    parser.add_argument("--width", type=int, default=1024)
    parser.add_argument("--height", type=int, default=768)
    parser.add_argument("--seed", type=int, default=None)
    parser.add_argument("--variant", type=str, default="emerald", choices=["emerald", "crimson", "amethyst"]) 
    parser.add_argument("--out", type=str, default="/workspace/outputs/dragon.png")
    args = parser.parse_args()

    img = generate(width=args.width, height=args.height, seed=args.seed, variant=args.variant)
    img.save(args.out)
    print(args.out)
