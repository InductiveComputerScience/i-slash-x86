#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.a = 31000;
	ts.b = 26000;
	ts.c = -31000;
	ts.d = 26000;

	test(&ts);

	printf("%d +| %d\n", ts.a, ts.b);
	printf("= %d\n", ts.x);
	printf("%d -| %d\n", ts.c, ts.d);
	printf("= %d\n", ts.y);

	if(ts.x == 32767 && ts.y == -32768){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
