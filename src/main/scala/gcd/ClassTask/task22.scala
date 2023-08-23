package ClassTask

import chisel3 . _
import chisel3.util._

class Tx extends Module {
val io = IO (new Bundle {
   val tx = Input (Bool())
   val readyi = Input (Bool())
   val valido = Output(Bool())
   val datao = Output(UInt(4.W))

})

var statecheck = RegInit(false.B)

io.valido := statecheck 
io.datao := statecheck

when (io.tx === 1.B){
    io.valido := 1.B
}

when (io.tx === 1.B && io.valido === 1.B){
    statecheck := 1.B
}
.otherwise{
    statecheck := 0.B

}

when (statecheck){
    io.datao := 4.U
    io.valido := 0.B
}
.otherwise{
    io.datao := 0.U
}


}

class Rx extends Module {
val io = IO (new Bundle {
   val busy = Input (Bool())
   val readyo = Output (Bool())
   val validi = Input(Bool())
   val datai = Input(UInt(4.W))

})

val RxState  = RegInit(false.B)


io.readyo := RxState

when (io.busy){

}


when(io.busy === 0.B && io.validi === 1.B){
    RxState := 1.B
}
.otherwise{
    RxState := 0.B
}

when (RxState){
    io.readyo := 1.B
}

}
class Main extends Module {
    val io = IO(new Bundle{
        val txinp = Input(Bool())
        val busyInp = Input(Bool())
        val datacheck = Output(UInt(4.W))

    }
    )

 val rx1 = Module(new Rx)
 val tx1 = Module (new Tx)

 rx1.io.busy := io.busyInp
 tx1.io.tx := io.txinp

 tx1.io.readyi := rx1.io.readyo
 rx1.io.validi := tx1.io.valido
 rx1.io.datai := tx1.io.datao
 io.datacheck := rx1.io.datai

}