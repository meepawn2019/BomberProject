package com.bomb.character;

import java.awt.*;

public abstract class Monster extends Character implements CanMove {
    public abstract void  doiHuong();
    public abstract Rectangle getBound();
}
