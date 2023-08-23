package ClassTask

import chisel3 . _
import chisel3.util._


class TxModule extends Module {
  val io = IO(new Bundle {
    val tx = Input(Bool())
    val ready = Input(Bool())
    val valid = Output(Bool())
    val data = Output(UInt(8.W))
  })

   val idle :: tx :: valid :: Nil = Enum(3)

  val state = RegInit(idle)

  val dataReg = Reg(UInt(8.W))
  val sendData = RegInit(false.B)
  
//   val sendDataValid = RegInit(false.B)
  // io.valid:= false.B

  switch(state){
    is(idle){
      when(~reset.asUInt()(0)) {
         state := tx
      }
      is(tx){
        when(io.tx===0.B){
          dataReg:=0.U
        }
        when(io.tx===1.B){
          io.valid:= 1.B
          dataReg:=0.U
          state:= valid
        }
      }

      is(valid){
        when(io.valid===1.B && io.ready===1.B){
           dataReg:=4.U
           state := tx
           io.valid:=0.B
        }
      }

    }



  }
  
  
  //   when(state === idle) {
    
  //   when(~reset.asUInt()(0)) {
  //     state := tx
  //   }
  // }
  // .elsewhen(state === tx) {
  //   when(io.tx) {
  //     state := valid
  //     io.valid:= 1.B
  //     dataReg := 0.U 
  //     sendData := true.B
  //   }
  // }
  // .elsewhen(state === valid) {
  //   when(io.valid===1.B && io.ready===1.B) {
  //     dataReg:=4.U
  //     state := tx
  //     //io.valid:=0.B
  //   }
  // }

  
   io.data := dataReg

//   when(state === tx) {
//     sendDataValid := io.tx
//   }.otherwise {
//     sendDataValid := false.B
//   }
}

class RxModule extends Module {
  val io = IO(new Bundle {
    val busy = Input(Bool())
    val valid = Input(Bool())
    val data = Input(UInt(8.W))
    val ready = Output(Bool())
  })

  val readyReg = RegInit(false.B)

  when(io.busy) {
    readyReg := 0.B
  }.otherwise {
    readyReg := 1.B
  }

  io.ready := readyReg
}

class TxRxStateMachine extends Module {
  val io = IO(new Bundle {
    val tx = Input(Bool())
    val busy = Input(Bool())
    val valid = Output(Bool())
    val data = Output(UInt(8.W))
    val ready = Output(Bool())
  })

  val txModule = Module(new TxModule)
  val rxModule = Module(new RxModule)

  txModule.io.tx := io.tx
  txModule.io.ready := rxModule.io.ready
  io.valid := txModule.io.valid
  io.data := txModule.io.data

  rxModule.io.busy := io.busy
  rxModule.io.valid := io.valid
  rxModule.io.data := io.data
  io.ready := rxModule.io.ready
}


// class Tx extends Module {
// val io = IO (new Bundle {
//    val tx = Input (Bool())
//    val readyi = Input (Bool())
//    val valido = Output(Bool())
//    val datao = Output(UInt(4.W))

// })
//  io.datao:=0.U
//  val state = RegInit(0.U(2.W))
// when(state === 0.U){
//     io.valido:=0.B
//     io.datao:=0.U
// }
// .elsewhen(state===1.U){
//     when(io.tx===1.U){
//         state:= state + 1.U
//         io.valido:=1.B
//     }
//     .elsewhen((io.tx===1.B && io.valido===1.B && io.readyi===1.U)){
//         io.datao:=4.U
//         state:= state-1.U
//     }
// }



// }

// class Rx extends Module {
// val io = IO (new Bundle {
//    val busy = Input (Bool())
//    val readyo = Output (Bool())
//    val validi = Input(Bool())
//    val datai = Input(UInt(4.W))

// })
// when(io.busy===1.B){
//     io.readyo:=0.B
// }
// .otherwise{
//     io.readyo:=1.B
// }


// }
// class Main extends Module {
//     val io = IO(new Bundle{
//         val txinp = Input(Bool())
//         val busyInp = Input(Bool())
//         val datacheck = Output(UInt(4.W))

//     }
//     )

//  val rx1 = Module(new Rx)
//  val tx1 = Module (new Tx)
//     rx1.io.busy := io.busyInp
//     tx1.io.tx := io.txinp
//  val rst_wire = WireInit(~reset.asUInt()(0))
// val statee = RegInit(0.U(2.W))
// val cycle = RegInit(0.U(2.W))

// switch(statee){
//     is(0.U){
//         rst_wire:= 0.U
//         statee:= statee + 1.U
//         tx1.state := statee
//     }
//     is(1.U){
//         rst_wire:=0.U

//         when(io.txinp && statee===1.U){
//             tx1.io.valido := 1.U
//             tx1.state := statee
//             statee := statee + 1.U
//         }
//     is(2.U){
//         rst_wire :=0.U

//         when(statee ===2.U && ~io.busyInp && tx1.io.valido === 1.U){
//             tx1.state := statee
//             rx1.io.datai := tx1.io.datao
//             statee := statee -1.U
//             tx1.state := statee
//         }

//     }
        
//     }
// }

 

//  when (reset.asUInt()(0)){
//     statee := 0.U
//     tx1.state:=statee
//  }
//  .elsewhen(~reset.asUInt()(0)){
//     statee := 1.U
//     tx1.state:=statee
//  }



// when (io.txinp === 1.U){
//     statee := statee +1.U
//     tx1.state:=statee
// }



//  tx1.io.readyi := rx1.io.readyo
//  rx1.io.validi := tx1.io.valido
//  rx1.io.datai := tx1.io.datao
//  io.datacheck := rx1.io.datai

// }