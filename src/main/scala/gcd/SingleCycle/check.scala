package SingleCycle

import chisel3. _
import chisel3. util . _

class check extends Module{
    val io=IO(new Bundle{
        val insin =Input(UInt(32.W))
        val pcselc = Input(Bool())
        val insout = Output(UInt(32.W))
    })

    // val hold = RegInit(0.U(32.W))
    // hold := io.insin

    val jump = Reg(Bool())

    when(io.pcselc){
        io.insout :=0.U
        jump:=1.B
    }
    .elsewhen(jump){
        io.insout :=0.U
        jump:=0.B

    }
    .otherwise{
        io.insout := io.insin
    }


}