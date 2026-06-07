#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

void print_m128_f32(__m128 reg);

int main(){
	struct testS ts;

	test(&ts);

	print_m128_f32(ts.a);
	print_m128_f32(ts.x);
	print_m128_f32(ts.y);
	print_m128_f32(ts.z);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}

void print_m128_f32(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four float values
    float *values = (float *)&reg;
    
    // Print in standard element order (from index 0 to 3)
    printf("f32 values: %f, %f, %f, %f\n", 
           values[0], values[1], values[2], values[3]);
}

