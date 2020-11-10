package io.shapez.core;

public class Vector {
    public float y;
    public float x;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public static String transformDirectionFromMultipleOf90(String direction, int angle) {
        if (angle == 0 || angle == 360) {
            return direction;
        }
        switch (direction) {
            case "top": {
                switch (angle) {
                    case 90:
                        return "right";
                    case 180:
                        return "bottom";
                    case 270:
                        return "left";
                    default:
                        return direction;
                }
            }
            case "right": {
                switch (angle) {
                    case 90:
                        return "bottom";
                    case 180:
                        return "left";
                    case 270:
                        return "top";
                    default:
                        return direction;
                }
            }
            case "bottom": {
                switch (angle) {
                    case 90:
                        return "left";
                    case 180:
                        return "right";
                    case 270:
                        return "top";
                    default:
                        return direction;
                }
            }
            case "left": {
                switch (angle) {
                    case 90:
                        return "top";
                    case 180:
                        return "right";
                    case 270:
                        return "bottom";
                    default:
                        return direction;
                }
            }
            default:
                return direction;
        }
    }

    public Vector divideScalar(int f) {
        return new Vector(this.x / f, this.y / f);
    }

    public Vector rotateFastMultipleOf90(int angle) {
        switch (angle) {
            case 360:
            case 0: {
                return new Vector(this.x, this.y);
            }
            case 90: {
                return new Vector(-this.y, this.x);
            }
            case 180: {
                return new Vector(-this.x, -this.y);
            }
            case 270: {
                return new Vector(this.y, -this.x);
            }
            default: {
                return new Vector();
            }
        }
    }

    public Vector addScalar(float f) {
        return new Vector(this.x + f, this.y + f);
    }

    public float length() {
        return (float) Math.hypot(this.x, this.y);
    }

    public Vector add(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y);
    }

    public Vector sub(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y);
    }
}
