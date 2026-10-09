package com.fufu.Desigin;

/**
 *
 * @author M <musededu25@gmail.com>
 * @since 2026/10/09 17:28
 */


public class Test {

    static void main() {
        //AbstractSkin skin = new HeimaSpecificSkin();

        AbstractSkin skin = new DefaultSpecificSkin();

        SouGouInput input = new SouGouInput(skin);

        input.display();
    }
}
