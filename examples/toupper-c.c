#include <stdlib.h>

void ToUpper(char *str, size_t len){
	for(size_t i = 0; i < len; i++){
		if(str[i] >= 'a' && str[i] <= 'z'){
			str[i] -= 32;
		}
	}	
}

void ToUpperBL(char *str, size_t len){
	for(size_t i = 0; i < len; i++){
		str[i] = str[i] * !(str[i] >= 'a' && str[i] <= 'z') + (str[i] - 32) * (str[i] >= 'a' && str[i] <= 'z');
	}	
}

void ToUpperBL2(char *str, size_t len){
	for(size_t i = 0; i < len; i++){
		_Bool lc = str[i] >= 'a' && str[i] <= 'z';
		str[i] = str[i] - 32 * lc;
	}	
}
