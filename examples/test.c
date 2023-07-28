#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#include <wchar.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>
#include <time.h>
#include <immintrin.h>

#include "ioc.h"

int main(){
	struct IndexOfCharacterS ioc;

	wchar_t *wstr = L"Hello, World!";
	uint64_t len = wcslen(wstr);
	uint32_t *data = malloc(sizeof(void*) + (len) * 4);
	*(uint64_t*)data = len;
	data += sizeof(void*) / 4;
	memcpy(data, (void*)wstr, len * 4);

	ioc.string = data;
	ioc.character = L'o';
	ioc.indexReference = (struct NumberReference *)malloc(sizeof(struct NumberReference));

	IndexOfCharacter(&ioc);

	printf("Found: %d, Index: %lu\n", ioc.found, ioc.indexReference->numberValue);

	return 0;
}





















