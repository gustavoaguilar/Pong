function love.load()
	score = 0;
	canScore = true;
	time = 0;
	gameOver = false;


	player = {};
	player.x = 100;
	player.y = love.graphics.getHeight() -32;
	player.speed = 3;
	player.size = 48;
	player.move = function(dx,dy)
		player.x = player.x + dx;
		player.y = player.y + dy;
	end

	ball = {};
	ball.x = 0;
	ball.y = 0;
	ball.speed = 3;
	ball.size = 10;
    ball.bdx = 0;
    ball.bdy = 0;

end

function love.update(dt)
    if(love.keyboard.isDown("right")) then
        if(player.x < love.graphics.getWidth() - player.size) then
            player.move(player.speed, 0)
        end
    end
    if(love.keyboard.isDown("left")) then
        if(player.x > 1) then
            player.move(-player.speed, 0)
        end
    end

    if(ball.x <= 0) then
        ball.bdx = ball.speed;
    end
    if(ball.x >= love.graphics.getWidth() - ball.size) then
        ball.bdx = -ball.speed;
    end
    if(ball.y <= 0) then
        ball.bdy = ball.speed;
        canScore = true;
    end
    if(ball.y >= love.graphics.getHeight() - ball.size) then
        gameOver = true;
        print("Game Over!");
    end
    if((ball.x >= player.x and ball.x <= player.x + player.size) and ball.y >= player.y-12) then
        ball.speed = ball.speed + 0.3;
        ball.bdy = -ball.speed;
        score = score +1;
        canScore = false;
    end
    ball.x = ball.x + ball.bdx;
    ball.y = ball.y + ball.bdy;
end

function love.draw()
    if(gameOver == false) then
        love.graphics.setColor(255,255,255);
        love.graphics.print("Score: "..tostring(score), 5, 20);
        love.graphics.rectangle("fill", player.x, player.y, player.size, 12);
        love.graphics.rectangle("fill", ball.x, ball.y, ball.size, ball.size);
    end
end