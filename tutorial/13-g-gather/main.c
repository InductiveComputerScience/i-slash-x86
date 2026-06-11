#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

#include "../simdlib.c"

int main(){
	struct testS ts;
	
	ts.data = malloc(1000);
	for(int i = 0; i < 100; i++){
		ts.data[i] = 2*i;
	}

	test(&ts);
	
	print_m128_u32(ts.a);
	print_m128_b32(ts.b);
	print_m128_u32(ts.x);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}
