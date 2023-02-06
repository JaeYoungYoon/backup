#include <stdio.h>
#include <stdlib.h> // rand 함수 쓰기 위해서 사용
#include <time.h> // srand 함수 쓰기 위해서 사용
#include <Windows.h> // system("cls");
#include <conio.h> // 키보드 입력

/*
void gotoxy(int x, int y){
	COORD pos = {x, y};
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), pos);
}
*/

void print_main(); // 메인 출력
void print_rule(); // 게임 설명
void print_mode(); // 모드 선택
void normal_mode(); // 일반 모드
void user_mode(); // 사용자 모드

void print_rule(){
	int n;

	printf("\t===================================================================\n\n");
	printf("\t\t\t<< 숫자 야구 게임 설명 >>\n\n");
	printf("\t===================================================================\n\n");
	printf("\t\t[1] 3자리 숫자가 랜덤으로 저장됩니다.\n");
	printf("\t\t[2] 저장된 수를 예상하여 입력합니다.\n");
	printf("\t\t[3] 자리와 숫자가 모두 일치하면 스트라이크,\n");
	printf("\t\t    자리는 다르지만 숫자가 일치하면 볼을 출력합니다.\n");
	printf("\t\t[4] 주어진 기회 안에 맞추면 성공!\n\n");
	printf("\t===================================================================\n\n");
	printf("\t\t\t1. 게임 시작   2. 메인 화면\n\n");
	printf("\t===================================================================\n\n");


	while(1){
		n=getch();
		if(n=='1') {system("cls"); print_mode();}
		else if(n=='2') {system("cls");print_main();}
		else printf("\t입력 오류! 다시 입력해주세요\n");
	}
}

void print_mode(){
	int n;

	printf("\t===================================================================\n\n");
	printf("\t\t\t<< 숫자 야구 게임 모드 >>\n\n");
	printf("\t===================================================================\n\n");
	printf("\t[1] 일  반 모드 : 3자리 숫자를 10번 안에 예상하여 맞추는 모드\n");
	printf("\t[2] 사용자 모드 : 사용자가 직접 자리수와 기회수를 정할 수 있는 모드\n\n");
	printf("\t===================================================================\n\n");
	printf("\t\t\t1. 일반 모드   2. 사용자 모드\n\n");
	printf("\t===================================================================\n\n");

	while(1){
		n=getch();
		if(n=='1') {system("cls"); normal_mode();}
		else if(n=='2') {system("cls");user_mode();}
		else printf("\t입력 오류! 다시 입력해주세요\n");
	}

}


void normal_mode(){
	int com[3], user[3], i, j, strike = 0, ball = 0, count = 0, replay;

	// com : 컴퓨터가 저장할 숫자의 변수
	// user : 사용자가 입력할 숫자의 변수
	// strike, ball : 판정
	// count : 기회 카운트

	srand((unsigned)time(NULL));
	// 매번 다르게 실행하기 위한 srand 함수 사용


	for(i = 0; i < 3; i++){
		com[i] = rand()%9+1;
	}
	// rand 함수로 1~9까지의 랜덤 수를 com배열에 넣음

	
	// 같은 수가 걸리지 않게 바꿈 (이것을 주석처리 할 수도 있음)
	while(com[0]==com[1] || com[1]==com[2] || com[0]==com[2]){ 
		for(i=0;i<3;i++){
			com[i]=rand()%9+1;
		}
	}
	
	printf("\t==== 숫자 야구 게임 ====\n");
	while(1){
		printf("\n");
		printf("\t남은 기회는 %d번입니다\n\t숫자를 입력하세요 : ", 10-count);
		scanf("%1d%1d%1d", &user[0], &user[1], &user[2]);
	
		for(i = 0; i < 3; i++){
			for(j = 0; j < 3; j++){
				if(user[i] == com[j]){ // 배열 중에서 숫자가 같은 것이 있는지 판단
					if(i == j) strike++; // 자리와 숫자가 같으면 스트라이크
					else ball++; // 아니면 볼
				}
			} 
		}
		count++; // 기회를 사용했으므로 카운트 증가
  
		if(strike == 3){ // 숫자를 모두 맞춘 경우
			printf("\t성공! %d 번만에 맞췄습니다\n", count);
			printf("\t정답 : %d %d %d \n", com[0], com[1], com[2]);
			break;
			}

		printf("\t%d 스트라이크 %d 볼\n",strike, ball);
		strike=0,ball=0; // 초기화

		if(count==10){ // 기회를 모두 쓴 경우
			printf("\t실패! 기회를 모두 사용했습니다\n");
			printf("\t정답 : %d, %d, %d \n",com[0],com[1],com[2]);
			break;
		}
	}

	printf("\n\t[ 1. 게임 재실행 2. 모드 변경 3. 메인 화면 ]\n");
	while(1){
	replay=getch();
	if(replay=='1') {system("cls"); normal_mode();}
	else if(replay=='2') {system("cls"); print_mode();}
	else if(replay=='3') {system("cls"); print_main();}
	else printf("\t입력 오류! 다시 입력해주세요\n");
	}

}

void user_mode(){
	int *com, *user, i, j, strike = 0, ball = 0, count = 0, number, ncount, replay;

	// com : 컴퓨터가 저장할 숫자의 변수
	// user : 사용자가 입력할 숫자의 변수
	// number : 자리수
	// ncount : 기회수
	// strike, ball : 판정
	// count : 기회 카운트

	srand((unsigned)time(NULL));

	// 매번 다르게 실행하기 위한 srand 함수 사용

	printf("\t==== 숫자 야구 게임 ====\n");
	printf("\t몇 자리 수로 진행하시겠습니까? ");
	scanf("%d", &number);

	com = (int *)malloc(sizeof(com) * number);
	user = (int *)malloc(sizeof(user) * number);

	for(i = 0; i < number; i++){
		com[i] = rand()%9+1;
	}
	// rand 함수로 1~9까지의 랜덤 수를 com에 넣음

	

	printf("\t기회는 몇 번으로 하시겠습니까? ");
	scanf("%d", &ncount);
	
	while(1){
		printf("\n");
		printf("\t남은 기회는 %d번입니다\n\t숫자를 입력하세요 : ", ncount - count);

		for(i=0; i<number; i++){
			scanf("%d", user);
		}
	
		for(i = 0; i < number; i++){
			for(j = 0; j < number; j++){
				if(user[i] == com[j]){ // 배열 중에서 숫자가 같은 것이 있는지 판단
					if(i == j) strike++; // 자리와 숫자가 같으면 스트라이크
					else ball++; // 아니면 볼
				}
			} 
		}
		count++; // 기회를 사용했으므로 카운트 증가
  
		if(strike == number){ // 숫자를 모두 맞춘 경우
			printf("\n\t성공! %d 번만에 맞췄습니다\n", count);
			printf("\t정답 :");
			for(i=0; i<number; i++){
				printf("%d ", com[i]);
			}
			break;
		}

		printf("\t%d 스트라이크 %d 볼\n",strike, ball);
		strike=0,ball=0; // 초기화

		if(count==ncount){ // 기회를 모두 쓴 경우
			printf("\n\t실패! 기회를 모두 사용했습니다\n");
			printf("\t정답 :");
			for(i=0; i<number; i++){
				printf("%d ", com[i]);
			}
			break;
		}
	}

	printf("\n\t[ 1. 게임 재실행 2. 모드 변경 3. 메인 화면 ] \n");
	while(1){
	replay=getch();
	if(replay=='1') {system("cls"); user_mode();}
	else if(replay=='2') {system("cls"); print_mode();}
	else if(replay=='3') {system("cls"); print_main();}
	else printf("\t입력 오류! 다시 입력해주세요\n");
	}
}


void print_main(){
	int n;
	
	printf("\t=================================================\n\n");
	printf("\t\t\t숫자 야구 게임\n\n");
	printf("\t=================================================\n\n");
	printf("\t\t\t1. 게임 방법 설명\n\n");
	printf("\t\t\t2. 게임 하기\n\n");
	printf("\t\t\t3. 게임 종료\n\n");
	printf("\t=================================================\n");
	
	while(1){
	n=getch();
	if(n=='1') {system("cls"); print_rule();}
	else if(n=='2') {system("cls"); print_mode();}
	else if(n=='3') {printf("\t게임을 종료합니다.\n\n");exit(1);}
	else printf("\t입력 오류! 다시 입력해주세요\n");
	}
}


void main(){
	print_main();
}