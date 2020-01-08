/*
	Made by: Gustavo Henrique Aguilar
	Version: 1.0
*/
#include<ncurses.h>
#include<unistd.h>
#include<time.h>
#include<stdlib.h>

int main(int argc, char const *argv[]) {
  char key, running, move = 1;
  int i,px=10,py=1, x=0, y=0, dx=1, dy=1, points=0;
	
  srand((unsigned int) time(NULL));
  x = (rand()%17)+1;

  initscr();
  cbreak();
  nodelay(stdscr, 1);
  noecho();
  curs_set(0);

  running = 1;

  while(running){
    key = getch();
    if(key == 'q' )running = 0;
    if(key == 'a'){
      if(px>1){
        px -= move;
      }
    }
    if(key == 's'){
      if(px<14){
        px += move;
      }
    }
    if(key == 'r'){
      x = (rand()%17)+1;;
      y=1;
      dx=1;
      dy=1;
      points=0;
      move=1;
    }

    if(y>=15){
      //HIT THE TABLE
      if(x >= px && x <= px+5){
       dy = -1;
       points++;
     }else{
       dy=0;
       dx=0;
       move = 0;
     }
    }
    if(y==1) dy = 1;
    if(x==18) dx =-1;
    if(x==1) dx =1;

    y += dy;
    x += dx;

    clear();
    printw("####################  Points: %d\n",points);
    for(i=1; i<=15; i++){
      printw("#                  #\n");
    }
    printw("####################\n");
    printw("'A' and 'S' to move\n");
    printw("Press 'Q' to quit\n");
    printw("Press 'R' to restart\n");
    move(y, x);
    printw("o");
    move(15,px);
    printw("TTTTT");

    refresh();
    usleep(100000);

  }

  echo();
  endwin();
  return 0;
}
