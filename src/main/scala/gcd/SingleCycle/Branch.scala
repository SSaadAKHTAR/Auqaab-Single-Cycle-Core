package SingleCycle

import chisel3. _
import chisel3. util . _

class Branch_Control extends Module{
    val io=IO(new Bundle{
        val fun3 =Input(UInt(3.W))
        val ina = Input(UInt(32.W))
        val inb = Input(UInt(32.W))
        val br_taken = Output(Bool())
    })
    io.br_taken:=0.B
    switch(io.fun3){
        is(0.U){
            when(io.ina===io.inb){
                io.br_taken:=1.B
            }
            .otherwise{
                io.br_taken:=0.B
            }

        }
        is(1.U){
            when(io.ina=/=io.inb){
                io.br_taken:=1.B
            }
            .otherwise{
                io.br_taken:=0.B
            }
        }
        is(4.U){
            when(io.ina<io.inb){
                io.br_taken:=1.B
            }
            .otherwise{
                io.br_taken:=0.B
            }
        }
        is(5.U){
            when(io.ina>=io.inb){
                io.br_taken:=1.B
            }
            .otherwise{
                io.br_taken:=0.B
            }
        }
        is(6.U){
            when(io.ina<io.inb){
                io.br_taken:=1.B
            }
            .otherwise{
                io.br_taken:=0.B
            }

        }
        is(7.U){
            when(io.ina>=io.inb){
                io.br_taken:=1.B
            }
            .otherwise{
                io.br_taken:=0.B
            }

        }
        
        
    }
}

