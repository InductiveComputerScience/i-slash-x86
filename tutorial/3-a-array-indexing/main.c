#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = malloc(sizeof(double) * 100);
	ts.x[50] = 1.2345;

	test(&ts);

	printf("test(...) = %f\n", ts.y);

	return 0;
}
