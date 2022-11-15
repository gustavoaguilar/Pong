#include "raylib.h"

#define SCREEN_WIDTH (320)
#define SCREEN_HEIGHT (480)

#define WINDOW_TITLE "Sort of a Pong - raylib"

int main(void){

    Vector2 playerPosition = { SCREEN_WIDTH/2.0f, SCREEN_HEIGHT - 20.0f };
    int player_width = 60;
    int playerSpeed = 4;
    int score = 0;

    Vector2 ballPosition = { SCREEN_WIDTH/2.0f, SCREEN_HEIGHT/2.0f };
    Vector2 ballSpeed = { 5.0f, 4.0f };

    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, WINDOW_TITLE);
    SetTargetFPS(60);

    while (!WindowShouldClose()){
        // take the input
        if(IsKeyDown(KEY_LEFT)){
            if(playerPosition.x > playerSpeed){
                playerPosition.x -= playerSpeed;
            } else {
                playerPosition.x = 0;
            }
        }
        if(IsKeyDown(KEY_RIGHT)){
            if(playerPosition.x < SCREEN_WIDTH - (player_width + playerSpeed)){
                playerPosition.x += playerSpeed;
            }else {
                playerPosition.x = SCREEN_WIDTH - player_width;
            }
        }

        // tick
        ballPosition.x += ballSpeed.x;
        ballPosition.y += ballSpeed.y;

        // bounce sides
        if ((ballPosition.x >= (SCREEN_WIDTH - 5)) || (ballPosition.x <= 10)){
            ballSpeed.x *= -1.0f;
        }
        // bounce top
        if(ballPosition.y <= 10){
            ballSpeed.y *= -1.0f;
        }
        // game over or score
        if (ballPosition.y >= (SCREEN_HEIGHT - 5)){
            ballSpeed.x = 0;
            ballSpeed.y = 0;
            playerSpeed = 0;
        }else if ((ballPosition.y >= (SCREEN_HEIGHT - 20)) &&
                  ballPosition.x > playerPosition.x && 
                  ballPosition.x < playerPosition.x + player_width){
            ballSpeed.y *= -1.0f;
            score++;
        }
                        
        BeginDrawing();

        ClearBackground(BLACK);
        DrawRectangle(playerPosition.x, playerPosition.y, player_width, 10, WHITE);
        DrawCircleV(ballPosition, (float)10, WHITE);
        DrawText(TextFormat("%i", score), 10, 10, 40, LIGHTGRAY);

        EndDrawing();
    }

    CloseWindow();

    return 0;
}