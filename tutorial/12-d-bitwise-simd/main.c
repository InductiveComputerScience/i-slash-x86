#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

void print_m128_u64(__m128 reg);
void print_m128_u32(__m128 reg);
void print_m128_u8(__m128 reg);
void print_m128_s8(__m128 reg);
void print_m128_b8(__m128 reg);
void print_m128_f16(__m128 reg);
void print_m128_f32(__m128 reg);
void print_m128_f64(__m128 reg);
void print_m128_b128(__m128 reg);
void print_m128_u32x(__m128 reg);

int main(){
	struct testS ts;

	test(&ts);

	print_m128_u64(ts.a);
	print_m128_u64(ts.b);
	
	print_m128_b128(ts.x);
	print_m128_b128(ts.y);
	print_m128_b128(ts.z1);
	print_m128_b128(ts.z2);
	print_m128_b128(ts.z3);
	print_m128_b128(ts.z4);
	print_m128_b128(ts.z5);
	print_m128_b128(ts.z6);
	print_m128_b128(ts.z7);

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

void print_m128_f64(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of two double values
    double *values = (double *)&reg;
    
    // Print in standard element order (from index 0 to 1)
    printf("f64 values: %f, %f\n", 
           values[0], values[1]);
}

void print_m128_u64(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of two double values
    uint64_t *values = (uint64_t *)&reg;
    
    // Print in standard element order (from index 0 to 1)
    printf("u64 values: %.8lx, %.8lx\n", 
           values[0], values[1]);
}

void print_m128_u32(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four uint32_t values
    uint32_t *values = (uint32_t *)&reg;
    
    // Print in standard element order (from index 0 to 3)
    printf("u32 values: %u, %u, %u, %u\n", 
           values[0], values[1], values[2], values[3]);
}

void print_m128_u8(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four uint8_t values
    uint8_t *values = (uint8_t *)&reg;
    
    printf("u8 values: %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u\n", values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15]);
}

void print_m128_s8(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four int8_t values
    int8_t *values = (int8_t *)&reg;
    
    printf("s8 values: %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d\n", values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15]);
}

void print_m128_u32x(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four uint8_t values
    uint32_t *values = (uint32_t *)&reg;
    
    printf("b32 values: %.8x, %.8x, %.8x, %.8x\n", values[0], values[1], values[2], values[3]);
}

void print_m128_b8(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four uint8_t values
    uint8_t *values = (uint8_t *)&reg;
    
    printf("b8 values: %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x, %.2x\n", values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15]);
}

void print_m128_b128(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four uint8_t values
    uint8_t *values = (uint8_t *)&reg;
    
    printf("b8 values: %.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x%.2x\n", values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15]);
}

