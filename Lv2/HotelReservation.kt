import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HotelReservation {
    init{
        println("호텔 예약 프로그램입니다.")
    }

    //메뉴 목록
    private val menus = arrayOf(
        "방 예약",
        "예약 목록 출력",
        "예약 목록 (정렬) 출력",
        "시스템 종료",
        "금액 입금-출금 내역 목록 출력",
        "예약 변경/취소")

    //예약 목록
    private var reservationInfoCollection= ReservationInfoCollection()
    //입출금 내역 목록
    private var transactionInfoList = arrayOf<TransactionInfo>()
    
    fun start(){
        while(true) {
            //메뉴 목록 출력
            printMenu()

            var input = readLine()!!.toInt()
            when (input) {
                1 -> runMenu1()
                2 -> runMenu2()
                3 -> runMenu3()
                4 -> {
                    runMenu4()
                    return
                    }
                5 -> runMenu5()
                6 -> runMenu6()
                else -> println("존재하지 않는 메뉴입니다.")
            }
        }
    }

    private fun printMenu(){
        println("[메뉴]")
        for(i in 0 until menus.size){
            print("${i+1}. ${menus[i]}.\t")
        }
        println()
    }

    //메뉴 1. 방 예약
    private fun runMenu1(){
        //(1) 이름 입력 받기
        println("예약자분의 성함을 입력해주세요.")
        val name = readLine()!!

        //(2) 방 번호 입력받기
        var roomNum = 0
        while(roomNum < 100 || 999< roomNum) {
            println("예약할 방 번호를 입력해주세요.")
            roomNum = readLine()!!.toInt()

            if(roomNum < 100 || 999< roomNum) println("올바르지 않은 방 번호입니다. 방 번호는 100부터 999까지 입니다.")
        }

        //(3) 체크인 날짜 입력받기 & (4) 체크아웃 날짜 입력받기
        //오늘 날짜 받아오기
        var nowDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val nowDateStr = nowDate.format(formatter)

        var checkIn:String
        var checkOut:String
        while(true) {
            //체크인 날짜 입력
            checkIn = "00000000"
            while (checkIn.toInt() < nowDateStr.toInt()) {
                //println("${nowDateStr.toInt()} ${checkIn.toInt()}")
                println("체크인 날짜를 입력해주세요. (표기 방식: 20230631)")
                checkIn = readLine()!!

                if (checkIn.toInt() < nowDateStr.toInt()) println("체크인 날짜로 지난 날짜를 선책할 수 없습니다.")
            }

            //체크아웃 날짜 입력
            checkOut = "00000000"
            while (checkOut.toInt() <= checkIn.toInt()) {
                println("체크아웃 날짜를 입력해주세요. (표기 방식: 20230631)")
                checkOut = readLine()!!

                if (checkOut.toInt() <= checkIn.toInt()) println("체크아웃 날짜로 체크인 날짜보다 전이거나 같은 날짜를 선택할 수 없습니다.")
            }

            //예약 가능한 기간인지 확인
            if(reservationInfoCollection.isRoomAvailable(roomNum, checkIn, checkOut)) break;
            else println("해당 기간에 이미 방을 사용 중입니다. 다른 날짜를 선택해주세요.")
        }

        //(4) 예약비 입금 후 출금
        transactionInfoList += TransactionInfo(name, "예약비 입금", 50000)
        transactionInfoList += TransactionInfo(name, "예약비 출금", -50000)

        //(5) 호텔 예약 내역 저장하기
        val newReservationInfo = ReservationInfo(name, roomNum, checkIn, checkOut)
        reservationInfoCollection.addReservationInfo(newReservationInfo)

        println("호텔 예약이 완료되었습니다.")
    }

    //메뉴 2. 예약 목록 출력
    private fun runMenu2(){
        reservationInfoCollection.printReservationInfoCollection()
    }
    //메뉴 3. 예약 목록 (정렬) 출력
    private fun runMenu3(){
        reservationInfoCollection.printSortedReservationInfoCollection()
    }
    //메뉴 4. 시스템 종료
    private fun runMenu4(){
        println("시스템을 종료합니다.")
    }
    //메뉴 5.금액 입금-출금 내역 목록
    private fun runMenu5(){

    }
    //메뉴 6. 예약 변경/취소
    private fun runMenu6(){

    }
}
