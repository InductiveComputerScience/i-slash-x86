#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = 0x00F0000103000211;

	test(&ts);

	printf("test(...) = %ld\n", ts.y);

	if(ts.y == 8){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
