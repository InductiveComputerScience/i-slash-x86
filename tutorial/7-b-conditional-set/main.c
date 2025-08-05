#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.a = 2;
	ts.b = 2;

	test(&ts);

	printf("test(...) = %ld\n", ts.y);

	return 0;
}
