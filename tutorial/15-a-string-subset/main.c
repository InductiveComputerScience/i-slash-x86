#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>
#include <immintrin.h>
#include <string.h>

#include "test.h"

#include "../simdlib.c"

void print_bits_16(uint16_t value);

int main(){
	struct testS ts;
  _Bool success;
  char s1[16];
  char m1[16];
	char m2[16];
	char m3[16];
	char m4[16];
  
  memcpy(s1, "This is a test. ", 16);
  memcpy(m1, "Ta\0\0\0\0\0\0\0\0\0\0\0\0\0\0", 16);
  memcpy(m2, "AZaz\0\0\0\0\0\0\0\0\0\0\0\0\0", 16);
  memcpy(m3, "This____a______\0", 16);
  memcpy(m4, "test\0\0\0\0\0\0\0\0\0\0\0\0", 16);

  ts.s1len = 16;
  memcpy(&ts.s1, s1, ts.s1len);
  ts.s2len = 16;
  memcpy(&ts.m1, m1, ts.s2len);
  memcpy(&ts.m2, m2, ts.s2len);
  memcpy(&ts.m3, m3, ts.s2len);
  memcpy(&ts.m4, m4, ts.s2len);
  ts.m1len = strlen(m1);
	ts.m2len = strlen(m2);
	ts.m3len = strlen(m3);
	ts.m4len = strlen(m4);

	ts.i = 0;

	test(&ts);
	
	printf("%s\n", (char*)&ts.s1);
	
	print_bits_16(ts.mask1);
	print_bits_16(ts.mask2);
	print_bits_16(ts.mask3);
	print_bits_16(ts.mask4);


	/*if(ts.c == 1){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}


void print_bits_16(uint16_t value) {
    for (int i = 0; i < 16; i++) {
        uint16_t bit = (value >> i) & 1;
        printf("%u", bit);
    }
    printf("\n");
}

