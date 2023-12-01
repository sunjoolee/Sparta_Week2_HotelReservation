class TransactionInfoCollection {
    var transactionInfoList = arrayOf<TransactionInfo>()

    fun addNewTransactionInfo(newTransactionInfo: TransactionInfo){
        transactionInfoList += newTransactionInfo
    }

    fun printTransactionCollection(inputName: String) {
        var index = 0
        for(i in 0 until transactionInfoList.size){
            if(transactionInfoList[i].name != inputName) continue

            println("${index+1}. ${transactionInfoList[i].info} ${transactionInfoList[i].money}원")
            index++
        }

        if(index==0) println("해당 예약자의 입출금 내역을 찾을 수 없습니다.")
    }
}
