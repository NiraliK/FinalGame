package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.mygdx.game.Background;

public class DesktopLauncher {

    public static void main(String[] arg) {
        // place the new image with an atlas into a folder called blocks
        TexturePacker.process("Background", "blocks", "blocks");

        // used to start the desktop version of the game
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new Background(), config);
    }
}
