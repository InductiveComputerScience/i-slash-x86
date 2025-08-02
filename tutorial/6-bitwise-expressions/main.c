#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.d0 = 0xab;
	ts.d1 = 0xcd;
	ts.d2 = 0xef;

	test(&ts);

	printf("test(...) = %x\n", ts.y);

	return 0;
}
