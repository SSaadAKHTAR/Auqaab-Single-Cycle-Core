package SingleCycle

import chisel3 . _
import chisel3 . util . _
class Datamem extends Module {
  val io = IO(new Bundle {
    val Wr_en = Input(Bool())
    val addr = Input(UInt(32.W))
    val Din = Input(UInt(32.W))
    val Dout = Output(UInt(32.W))
    val fun3 = Input(UInt(3.W))
    //val mask = Input(Vec(4,Bool()))
  })

  val memory = Mem (32 ,Vec (4 , UInt ( 8 . W ) ) )
  val mask = Reg(Vec(4,Bool()))
  val data = Reg(Vec(4,UInt(8.W)))

  data(0) := io.Din(7,0)
  data(1) := io.Din(15,8)
  data(2) := io.Din(23,16)
  data(3) := io.Din(31,24)


  when(io.fun3 === 0.U){
    when(io.addr(1,0)=== 0.U){
      data(0) := io.Din(7,0)
      data(1) := io.Din(15,8)
      data(2) := io.Din(23,16)
      data(3) := io.Din(31,24)

      mask(0) := 1.B
      mask(1) := 0.B
      mask(2) := 0.B
      mask(3) := 0.B
    }
    .elsewhen(io.addr(1,0) === 1.U){
      mask(0) := 0.B
      mask(1) := 1.B
      mask(2) := 0.B
      mask(3) := 0.B

      data(0) := io.Din(7,0)
      data(1) := io.Din(7,0)
      data(2) := io.Din(23,16)
      data(3) := io.Din(31,24)

    }
    .elsewhen(io.addr(1,0) === 2.U){
      mask(0) := 0.B
      mask(1) := 0.B
      mask(2) := 1.B
      mask(3) := 0.B

      data(0) := io.Din(7,0)
      data(1) := io.Din(15,8)
      data(2) := io.Din(7,0)
      data(3) := io.Din(31,24)

    }
     .elsewhen(io.addr(1,0) === 3.U){
      mask(0) := 0.B
      mask(1) := 0.B
      mask(2) := 0.B
      mask(3) := 1.B

      data(0) := io.Din(7,0)
      data(1) := io.Din(15,8)
      data(2) := io.Din(23,16)
      data(3) := data(0)

    }
  }
  .elsewhen(io.fun3 === 1.U){
    
  }


  memory.write(io.addr(31,2),data,mask)

 
  

  when (io.Wr_en){
    memory.write(io.addr,x,io.mask)
    x := memory.read(io.addr)
    when(io.mask(0) ===1.B && io.mask(1) ===1.B && io.mask(2) ===1.B && io.mask(3) ===1.B){

      io.Dout(7,0) := x(0)
      io.Dout(15,8) := x(1)
      io.Dout(23,16) := x(2)
      io.Dout(24,31) := x(3)
  }
    //io.Dout := memory.read(io.addr)
  }

  x := memory.read(io.addr)

  when(io.mask(0) ===1.B && io.mask(1) ===1.B && io.mask(2) ===1.B && io.mask(3) ===1.B){

    io.Dout(7,0) := x(0)
    io.Dout(15,8) := x(1)
    io.Dout(23,16) := x(2)
    io.Dout(24,31) := x(3)
  }
}