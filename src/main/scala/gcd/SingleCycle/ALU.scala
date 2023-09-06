package SingleCycle
import chisel3._ 
import chisel3.util._ 
// import _root_.Lab4.fn3

object alu_op{
    val alu_add = 0.U(4.W)
    val alu_Sub = 1.U(4.W)
    val alu_Sll = 2.U(4.W)
    val alu_Slt = 4.U(4.W)
    val alu_Sltu = 6.U(4.W)
    val alu_Xor = 8.U(4.W)
    val alu_Srl = 10.U(4.W)
    val alu_Sra = 11.U(4.W)
    val alu_Or = 12.U(4.W)
    val alu_And = 14.U(4.W)
}


import  alu_op._

class IOBundle extends Bundle  {
    val in_A = Input(UInt(32.W))
    val in_B = Input(UInt(32.W))
    val alu_op = Input(UInt(4.W))
    val out = Output(UInt(32.W))
    // val sum = Output(UInt(32.W))
}
class ALUS extends Module{
    val io = IO(new IOBundle)
    val sum = io . in_A + Mux( io.alu_op(0) , (-io .in_B) , io.in_B )
    val shamt = io . in_B (4 ,0) . asUInt
    val shin = Mux(io.alu_op(3), io.in_A , Reverse(io.in_A))
    val shiftr = (Cat(io.alu_op(0) && shin(32-1), shin).asSInt >> shamt)(32-1,0)
    val shiftl = Reverse(shiftr)


    io.out := 0.U
    switch ( io.alu_op ){
    is (alu_add){
        io.out:=sum
    }
    is (alu_Sub){
        io.out:=sum
    }
    is (alu_Slt){
        when(io.in_A.asSInt < io.in_B.asSInt()){
            io.out := 1.U
        }
        .otherwise{
            io.out := 0.U

        }
    }
    is (alu_Sltu){
        when(io.in_A.asUInt() < io.in_B.asUInt()){
            io.out := 1.U
        }
        .otherwise{
            io.out := 0.U

        }
    }
    is (alu_Sra){
        io.out:=shiftr
    }
    is (alu_Srl){
        io.out:=shiftr
    }
    is (alu_Sll){
        io.out:= shiftl
    }
    is (alu_And){
        io.out:=( io . in_A & io . in_B )
    }
    is (alu_Or){
        io.out:=( io . in_A | io . in_B )
    }
    is (alu_Xor){
        io.out:=( io . in_A ^ io . in_B )
    }
    




}



}




