#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.a = 14;
	ts.b = -3;

	ts.c = 0xAFFFFFFFFFFFFF;
	ts.d = 0xBFFFFFFFFFFFFF;
	ts.e = 0xCFFFFFFFFFFFFF;

	test(&ts);

	printf("test(...) = %ld, %ld\n", ts.x, ts.y);
	printf("test(...) = %lx\n", ts.z);

	return 0;
}
