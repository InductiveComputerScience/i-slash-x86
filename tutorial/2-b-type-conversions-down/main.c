#include <stdio.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = -21.0;

	test(&ts);

	printf("x = %f\n", ts.x);
	printf("a = %f\n", ts.a);
	printf("b = %d\n", ts.b);
	printf("y = %d\n", ts.y);
	printf("test(%f) = %d\n", ts.x, ts.y);

	return 0;
}
