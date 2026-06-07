#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

#include "../simdlib.c"


int main(){
	struct testS ts;

	test(&ts);

	print_m128_u8(ts.a);
	print_m128_u8(ts.b);
	print_m128_u8(ts.x);
	print_m128_u8(ts.y);
	print_m128_u8(ts.z);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}

