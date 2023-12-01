class ReservationInfoCollection {
    var reservationInfoList = arrayOf<ReservationInfo>()

    fun addReservationInfo(newReservationInfo: ReservationInfo){
        reservationInfoList += newReservationInfo
    }

    private fun printReservationInfoList(){
        for(i in 0 until reservationInfoList.size){
            val name = reservationInfoList[i].name
            val roomNum = reservationInfoList[i].roomNum
            var checkIn = reservationInfoList[i].checkIn
            var checkOut = reservationInfoList[i].checkOut

            checkIn = checkIn.substring(0,4)+"-"+checkIn.substring(4,6)+"-"+checkIn.substring(6)
            checkOut = checkOut.substring(0,4)+"-"+checkOut.substring(4,6)+"-"+checkOut.substring(6)
            println("${i+1}. 예약자: ${name}, 방 번호: ${roomNum}, 체크인: ${checkIn}, 체크아웃:${checkOut}")
        }
    }

    fun printReservationInfoCollection(){
        println("호텔 예약 목록입니다.")
        printReservationInfoList()
    }
    fun printSortedReservationInfoCollection(){
        println("호텔 예약 목록입니다. (정렬 완료)")
        reservationInfoList.sortBy{it.checkIn}
        printReservationInfoList()
    }

    fun isRoomAvailable(inputRoomNum:Int, inputCheckIn:String, inputCheckOut:String):Boolean{
        //(1) a---a b---b true
        //(2) a---b===a---b false
        //(3) a---b===b---a false
        //(4) b---a===b---a false
        //(5) b---b a---a true

        for(i in 0 until reservationInfoList.size){
            if(reservationInfoList[i].roomNum != inputRoomNum) continue

            //(2) & (3)
            if(reservationInfoList[i].checkIn <= inputCheckIn && inputCheckIn <= reservationInfoList[i].checkOut) return false
            //(3) & (4)
            if(reservationInfoList[i].checkIn <= inputCheckOut && inputCheckOut <= reservationInfoList[i].checkOut) return false
        }
        return true
    }
}
