#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.y = 0;
	ts.a = 3;
	ts.b = 3;
	ts.c = 4;

	printf("test(...) = %d, %ld, %ld, %ld\n", ts.y, ts.a, ts.b, ts.c);

	test(&ts);

	printf("test(...) = %d, %ld, %ld, %ld\n", ts.y, ts.a, ts.b, ts.c);

	if(ts.y == 1 && ts.a == 3 && ts.b == 4){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	ts.y = 0;
	ts.a = 2;
	ts.b = 3;
	ts.c = 4;

	printf("test(...) = %d, %ld, %ld, %ld\n", ts.y, ts.a, ts.b, ts.c);

	test(&ts);

	printf("test(...) = %d, %ld, %ld, %ld\n", ts.y, ts.a, ts.b, ts.c);

	if(ts.y == 0 && ts.a == 3 && ts.b == 3){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
