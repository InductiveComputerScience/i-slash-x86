#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "test.h"

int StringsEqual(wchar_t *a, size_t aLength, wchar_t *b, size_t bLength);

int main(){
	struct testS ts;
	struct GetTSS t1, t2;

  ts.al = 100;
  ts.a = calloc(ts.al, sizeof(uint32_t));
  ts.bl = 100;
  ts.b = calloc(ts.bl, sizeof(uint32_t));

	ts.b[99] = 1;

	GetTS(&t1);
	test(&ts);
	GetTS(&t2);

	printf("test(...) = %lu, %lu, %lu, %d\n", t1.t, t2.t, t2.t - t1.t, ts.equal);

	GetTS(&t1);
	ts.equal = StringsEqual(ts.a, ts.al, ts.b, ts.bl);
	GetTS(&t2);

	printf("test(...) = %lu, %lu, %lu, %d\n", t1.t, t2.t, t2.t - t1.t, ts.equal);

	GetTS(&t1);
	ts.equal = memcmp(ts.a, ts.b, ts.al);
	GetTS(&t2);

	printf("test(...) = %lu, %lu, %lu, %d\n", t1.t, t2.t, t2.t - t1.t, ts.equal);

	return 0;
}
