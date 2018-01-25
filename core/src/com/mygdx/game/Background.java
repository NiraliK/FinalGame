package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Background extends ApplicationAdapter {
    // sprite batch
    private SpriteBatch batch;
    // texture atlas that will help load in the images from the big image
    // this was created from running the texture packer (in Desktop Launcher)
    
    private TextureAtlas atlas;
    // diamond image as the blocks
    private TextureRegion diamond;
   
    // current block's x and y positions
    private int currentY;
    private int currentX;

    // width and size of the diamond
    private int currentH;
    private int currentW;

    // width and height of screen
    public int width;
    public int height;
    
    // array of coordinates for every block added
    private Coordinates[] coordinates;

    // camera and viewport
    private OrthographicCamera camera;
    private Viewport view;
    private int camSpeed;

    // make a variable that shows the maximum number of squares on the screen at once
    private int maxOnScreen;

    // x and y coordinates
    public int x;
    public int y;

    // for the player movement
    // players x coordinate
    private float playerx;
    // players y coordinate
    private float playery;
    // to add to the player's x and y coordinates
    private float dx;
    private float dy;

    //image of the ball
    Texture img;

    // point system
    // get the score of the game
    private int point;
    private String yourPointName;
    // use the standard font to output the score
    BitmapFont font;

    @Override
    public void create() {

        // set up the camera and view
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // move the camera to the center
        this.camera.position.set(800 / 2, 600 / 2, 0);
        // make sure to apply the changes
        this.camera.update();

        this.view = new FitViewport(800, 600, camera);
        view.apply();

        // diamond image
        this.atlas = new TextureAtlas("blocks/blocks.atlas");
        batch = new SpriteBatch();
        diamond = atlas.findRegion("Diamond");

        // keep track of the diamonds
        int numberOfDiamonds = 10000;
        coordinates = new Coordinates[20000];

        // path can be either up right or up left
        String path = "upRight";

        // coordinates of first diamond (starting point)
        currentX = 400;
        currentY = 0;

        // size of the diamond
        currentH = 100;
        currentW = 100;

        // keeps track of how many diamond's are drawn
        int tracker = 0;

        // the max number of diamonds on the height of the screen divided by 50 because the blocks move up 50 y-coords every time
        maxOnScreen = height / 50;

        // set the camera speed
        camSpeed = 0;

        // coordinates to draw the diagram
        for (int i = maxOnScreen; i < numberOfDiamonds; i++) {
            int randomPath = random(1, 2);
            // the path is going up and right
            if (path == "upRight") {
                for (int j = 0; j <= randomPath - 1; j++) {
                    // add 100 to the x coordinate to right
                    currentX = currentX + (j * 50);
                    // add 100 to the y coordinate to move up
                    currentY = currentY + (j * 50);
                    // make the coordinates at the specific tracker the current coordinates so that it is the current image
                    // coordinates of next image rely on current image, and keep making next image current image
                    coordinates[tracker] = new Coordinates(currentX, currentY);
                    // add to the tracker
                    tracker++;
                }
                // make the path go left next so it is alternating between up left and up right
                path = "upLeft";
                // the path is going up and left
            } else {
                // the path is up left
                for (int j = 0; j <= randomPath - 1; j++) {
                    // subtract 100 from the x coordinate to move left
                    currentX = currentX - (j * 50);
                    // add 100 to the y coordinate to move up
                    currentY = currentY + (j * 50);
                    // make the coordinates at the specific tracker the current coordinates so that it is the current image
                    // coordinates of next image rely on current image, and keep making next image current image
                    coordinates[tracker] = new Coordinates(currentX, currentY);
                    // add to the tracker
                    tracker++;
                }
                path = "upRight";
            }
            // make sure the path does not go off the margins of the page
            // make the path turn right if it is too far left
            if (currentX <= 100) {
                path = "upRight";
            }
            // make the path turn left if it is too far right
            if (currentX >= 700) {
                path = "upLeft";
            }
        }

        // set the x posiiton for the player to be on the block
        this.dx = 420;
        // set the y position for the player
        this.dy = 30;
        // get the image of the player
        img = new Texture("ball.png");

        // start the score at 0
        point = 0;
        // output what the score is
        yourPointName = "Score: 0";
        // set the font of the score
        font = new BitmapFont();
    }

    @Override
    public void render() {

        // draw the background
        // make the background clear
        Gdx.gl.glClearColor(1, 1, 1, 1);
        //fill in the background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // make sure the player is not off the screen to move the camera
        // move the camera up slowly and increase the speed as the player gets higher
        if (dy >= 0 && dy >= (camera.position.y - 335)) {
            // move the camera with the player
            camera.position.y = (dy + 260) * 1;
            camera.position.y = camSpeed++;
        }
        // increase the speed
        if (dy >= 500 && dy >= (camera.position.y - 335)) {
            // move the camera with the player
            camera.position.y = (dy + 260) * 2;
            camera.position.y = camSpeed++;
        }
        // increase the speed
        if (dy >= 1000 && dy >= (camera.position.y - 335)) {
            // move the camera with the player
            camera.position.y = (dy + 260) * 3;
            camera.position.y = camSpeed++;
        }
        // increase the speed
        if (dy >= 1500 && dy >= (camera.position.y - 335)) {
            // move the camera with the player
            camera.position.y = (dy + 260) * 4;
            camera.position.y = camSpeed++;
        }
        // increase the speed
        if (dy >= 2000 && dy >= (camera.position.y - 335)) {
            // move the camera with the player
            camera.position.y = (dy + 260) * 5;
            camera.position.y = camSpeed++;
        }
        // increase the speed
        // max speed
        if (dy >= 3000 && dy >= (camera.position.y - 335)) {
            // move the camera with the player
            camera.position.y = (dy + 260) * 6;
            camera.position.y = camSpeed++;
        }

        // update the camera
        camera.update();
        // set the camera in order to work
        batch.setProjectionMatrix(camera.combined);

        // collision detection
        // get the ball's x position
        int ballX = (int) ((img.getWidth() / 2) + dx);
        // get the ball's y position
        int ballY = (int) ((img.getHeight() / 2) + dy);
        // get the coordinates of the diamonds
        Coordinates tile = new Coordinates(-10, -10);
        
       // check for any collisons
        for (int i = 0; i < coordinates.length; i++) {
            // make sure the ball is on top of the diamond
            if (ballX >= coordinates[i].x && ballX <= coordinates[i].x + 100 && ballY >= coordinates[i].y && ballY <= coordinates[i].y + 100) {
                tile.x = coordinates[i].x;
                tile.y = coordinates[i].y;
                // if the ball is not on a diamond then it fell off and the game ends
                break;
            }
        }
       

        //drawing the background, player and score
        //begin the drawing here
        batch.begin();
        // use a for loop to go through the positions of the blocks
        for (int i = maxOnScreen; i < coordinates.length; i++) {
            // make sure the coordinates are not null
            if (coordinates[i] != null) {
                // drawing the blocks 
                batch.draw(diamond, coordinates[i].x, coordinates[i].y, currentH, currentW);
            }
        }

        // draw the player at the specified location
        batch.draw(img, dx, dy);

        // movement for the player
        // if the right key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            // ball movement
            if (dy >= 0 && dy < 500) {
                // move the ball diagonally
                // move it to the right
                this.dx += 1;
                // move it up
                this.dy += 1;
                // draw the ball at that new location
                batch.draw(img, dx, dy);
            }

            // increase the speed
            if (dy >= 500 && dy < 1000) {
                // move it to the right
                this.dx += 2;
                // move it up
                this.dy += 2;
                // draw the ball at that new location
                batch.draw(img, dx, dy);
            }

            // increase the speed
            if (dy >= 1000 && dy < 1500) {
                // move it to the right
                this.dx += 3;
                // move it up
                this.dy += 3;
                // draw the ball at the new position
                batch.draw(img, dx, dy);
            }

            // increase the speed
            if (dy >= 1500 && dy < 2000) {
                // move it to the right
                this.dx += 4;
                // move it up
                this.dy += 4;
                // draw the ball at the new position
                batch.draw(img, dx, dy);
            }
            
            // increase the speed
            if (dy >= 2000 && dy < 3000) {
                // move it to the right
                this.dx += 5;
                // move it up
                this.dy += 5;
                // draw the ball at the new position
                batch.draw(img, dx, dy);
            }

            // increase the speed, this is the max speed
            if (dy >= 3000) {
                /// move it to the right
                this.dx += 6;
                // move it up
                this.dy += 6;
                // draw the ball at the new position
                batch.draw(img, dx, dy);
            }

            // add points to the score for every 100 coordinates it moves up
            point++;
            yourPointName = "Score: " + point / 100;

            // movement for the player if the left key is pressed
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            // start at a slow speed, increase the speed as the ball gets higher
            if (dy >= 0 && dy < 500) {
                // move the ball diagonally
                // move it to the right
                this.dx += -1;
                // move it up
                this.dy += 1;
                // draw the ball at that new location
                batch.draw(img, dx, dy);
            }

            // increase the speed
            if (dy >= 500 && dy < 1000) {
                // move it to the right
                this.dx += -2;
                // move it up
                this.dy += 2;
                // draw the ball at that new location
                batch.draw(img, dx, dy);
            }

            // increase the speed
            if (dy >= 1000 && dy < 1500) {
                // move it to the right
                this.dx += -3;
                // move it up
                this.dy += 3;
                // draw the ball at the new position
                batch.draw(img, dx, dy);
            }
            
            // increase the speed
            if (dy >= 1500 && dy < 2000) {
                // move it to the right
                this.dx += -4;
                // move it up
                this.dy += 4;
                // draw the ball at the new position
                batch.draw(img, dx, dy);
            }

            // increase the speed
            if (dy >= 2000 && dy < 3000) {
                // move it to the right
                this.dx += -5;
                // move it up
                this.dy += 5;
                // draw the ball at the new position
                batch.draw(img, dx, dy);
            }

            // increase the speed, this is the max speed
            if (dy >= 3000) {
                /// move it to the right
                this.dx += -6;
                // move it up
                this.dy += 6;
                // draw the ball at the new position
                batch.draw(img, dx, dy);
            }
            // add points to the score for every 100 coordinates it moves up
            point++;
            yourPointName = "Score: " + point / 100;
        }
        // set the color of the score font
        font.setColor(0, 0, 0.2f, 1);
        // output the score
        font.draw(batch, yourPointName, (camera.position.x) - 370, (camera.position.y) + 275);

        // move the player to it's new location depending on what key the player pressed
        this.playerx = this.playerx + this.dx;
        this.playery = this.playery + this.dy;

        // end the drawing
        batch.end();

    }

    @Override
    public void dispose() {
    }

    // randomize the path for the diamonds
    int random(int min, int max) {
        int random = (int) (Math.random() * 2);
        int entry;
        if (1 <= 2) {
            entry = 1;
        } else {
            entry = 2;
        }
        return random + entry;
    }

    // create the coordinates
    public class Coordinates {

        // coordinates for the diamonds
        public int x;
        public int y;

        public Coordinates(int X, int Y) {
            x = X;
            y = Y;
        }
    }

}
