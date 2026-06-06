#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

void print_m128_f32(__m128 reg);
void print_m128_b128(__m128 reg);

int main(){
	struct testS ts;

	test(&ts);

	print_m128_f32(ts.a);
	print_m128_f32(ts.b);
	print_m128_f32(ts.c);
	print_m128_f32(ts.x);
	print_m128_b128(ts.y);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}

void print_m128_f32(__m128 reg) {
    float *values = (float *)&reg;
   
    printf("f32 values: %f, %f, %f, %f\n", values[0], values[1], values[2], values[3]);
}

void print_m128_b128(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four uint8_t values
    uint8_t *values = (uint8_t *)&reg;
    
    printf("b8 values: %.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x\n", values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15]);
}



