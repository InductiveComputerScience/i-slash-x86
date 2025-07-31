#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.a = 5;

	test(&ts);

	printf("test(...) = %f\n", ts.y);

	return 0;
}
