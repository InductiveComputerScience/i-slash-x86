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

	ts.x = CreateArray(20);
	ts.y = CreateArray(20);

  for(int i = 0; i < 20; i++){
		ts.x[i] = i+1;
		printf("%.2x ", ts.x[i]);
	}
	printf("\n");

  for(int i = 0; i < 20; i++){
		printf("%.2x ", ts.y[i]);
	}
	printf("\n");

	test(&ts);

	printf("len: %ld\n", ts.l);

	success = true;

  for(int i = 0; i < 20; i++){
		printf("%.2x ", ts.x[i]);
	}
	printf("\n");

  for(int i = 0; i < 20; i++){
		printf("%.2x ", ts.y[i]);
		if(ts.y[i] != i+1){
			success = false;
		}
	}
	printf("\n");

	if(success){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
