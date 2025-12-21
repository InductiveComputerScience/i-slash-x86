#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
  struct testS ts;

  // Selects bits in x according to m
  //       0123456789ABCDEF  -- source ids
  ts.x = 0xFFFFFFFFFEDCBA00;
  ts.m = 0x00000000F0F0FFF0;
  //               B C DEF   -- selected ids
  //               C0B0A000   

  test(&ts);

  printf("test(...) = %lx\n", ts.y);

  if(ts.y == 0xC0B0A000){
    printf("success\n");
  }else{
    printf("fail\n");
  }

  return 0;
}
