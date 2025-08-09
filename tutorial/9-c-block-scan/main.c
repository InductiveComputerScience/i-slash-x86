#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>

#include "test.h"

void *CreateArray(uint64_t l){
	uint8_t *r;

	r = malloc(l+8);
	*(uint64_t*)r = l;
	r += 8;

	return (void*)r;
}

int main(){
	struct testS ts;
  _Bool success;

	success = true;

	ts.x = CreateArray(20);
	ts.y = 0x8;

  for(int i = 0; i < 20; i++){
		ts.x[i] = i+1;
		printf("%.2x ", ts.x[i]);
	}
	printf("\n");

	test(&ts);

	printf("index: %ld (20 means not found)\n", ts.i);

	if(ts.i == 7){
	}else{
		success = false;
	}

	ts.y = 0x0;
	test(&ts);
	printf("index: %ld (20 means not found)\n", ts.i);

	if(ts.i == 20){
	}else{
		success = false;
	}

	if(success){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
} 
