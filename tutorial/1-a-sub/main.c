#include <stdio.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = 21;

	test(&ts);

	printf("f(%ld) = %ld\n", ts.x, ts.retval);

	return 0;
}
