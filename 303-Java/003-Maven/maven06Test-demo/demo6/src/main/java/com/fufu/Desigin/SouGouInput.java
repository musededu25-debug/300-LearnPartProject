package com.fufu.Desigin;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/09 17:24
 */


public class SouGouInput {

    AbstractSkin skin ;

    public SouGouInput(AbstractSkin skin) {
        this.skin = skin;
    }

    public void display(){
        skin.disPlay();
    }
}
