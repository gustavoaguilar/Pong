/*
    Author: Gustavo Henrique Aguilar
    Date: 25/09/2017
    Version: 1.0
*/

#include<SFML/Graphics.hpp>
#include<string>

int main(int argc, char const *argv[]) {
    //Declare variables
    int score =0;
    float dx, dy, speed=5;
    dx=0;
    dy=0;

    //Create Window
    sf::RenderWindow window(sf::VideoMode(300, 400), "Pong SFML C++");
    window.setFramerateLimit(30);

    //Create Font (display score)
    sf::Font font;
    font.loadFromFile("Tuffy.ttf");

    //Create the Label (display score)
    sf::Text text("", font);
    text.setString(std::to_string(score));
    text.setCharacterSize(30);
    //text.setStyle(sf::Text::Bold);
    text.setColor(sf::Color::White);

    //Create a rectangle to represent the ball
    sf::RectangleShape ball(sf::Vector2f(10,10));
    ball.setFillColor(sf::Color::White);
    ball.setPosition(100, 100);

    //Create a rectangle to represent the player
    sf::RectangleShape player(sf::Vector2f(40,10));
    player.setFillColor(sf::Color::White);
    player.setPosition(130,385);

    //Game Loop
    while(window.isOpen()){
        //Get ball and Player coordinates
        sf::Vector2f bPos = ball.getPosition();
        sf::Vector2f pPos = player.getPosition();

        //Detect collisions
        if(bPos.x<0) dx=speed;
        else if(bPos.x>300-10) dx=-speed;
        if(bPos.y<0) dy=speed;
        else if(bPos.y>400-10){
            dy=0;
            dx=0;
        }
        if(bPos.x>= pPos.x && bPos.x<= pPos.x+40)
            if(bPos.y>= pPos.y-10){
                score++;
                text.setString(std::to_string(score));
                dy=-speed;
            }
    
        //Move the ball
        ball.move(dx, dy);

        //Input detection
        if(sf::Keyboard::isKeyPressed(sf::Keyboard::Space)){
            dx=speed;
            dy=speed;
            score=0;
        }
        if(sf::Keyboard::isKeyPressed(sf::Keyboard::Left))
            if(pPos.x>0)
                player.move(-speed, 0);
        if(sf::Keyboard::isKeyPressed(sf::Keyboard::Right))
            if(pPos.x<300-40)
                player.move(speed, 0);

        //Check if the window was closed
        sf::Event event;
        while(window.pollEvent(event)){
        if(event.type == sf::Event::Closed) window.close();
        }

        //Render
        window.clear();
        window.draw(ball);
        window.draw(player);
        window.draw(text);
        window.display();
    }
    return 0;
}
