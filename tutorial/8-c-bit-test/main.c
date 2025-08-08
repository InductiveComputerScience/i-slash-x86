#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = 0x0000001000100000ULL;

	test(&ts);

	printf("test(...) = %ld, %ld\n", ts.y, ts.z);

	if(ts.y == 1 && ts.z == 0){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
