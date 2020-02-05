//
//  TaskNoteRow.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 20/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TaskNoteRow: View {
    @State var ticketNote:TaskNote
    var body: some View {
        VStack{
            HStack{
                VStack{
                    VStack{
                        Image(systemName: "text.bubble")
                            .resizable()
                            .padding(.all, 8)
                    }.frame(width: 30, height: 30, alignment: .center)
                        .foregroundColor(Color.white)
                        .background(Color("aquaMarine"))
                        .clipped()
                        .clipShape(Circle())
                        .shadow(radius: 5)
                    VStack{
                        Text("")
                    }.frame(minWidth: 1, maxWidth: 1, minHeight: 0, maxHeight: .infinity)
                        .foregroundColor(.green)
                        .overlay(
                            Rectangle()
                                .strokeBorder(
                                    style: StrokeStyle(
                                        lineWidth: 1,
                                        dash: [4]
                                    )
                            )
                                .foregroundColor(.green)
                    )
                }
                .frame(alignment: .top)
                VStack{
                    HStack{
                        Text(self.ticketNote.userName!)
                            .foregroundColor(Color("warmGrey"))
                            .font(Fonts.RobotSectionTitle)
                            .bold()
                        Spacer()
                    }
                    HStack{
                        Text(self.ticketNote.creationTime!.convetToDateFromMySQL())
                            .font(Fonts.RobotRegular)
                            .foregroundColor(Color("brownishGrey"))
                        Spacer()
                    }.padding(.bottom, 15)
                    HStack{
                    
                    GuestJiniInformationText(information: self.ticketNote.notes!)
                        Spacer()
                    }
                   
                }.padding(.horizontal)
            }
        }.edgesIgnoringSafeArea(.all)
    }
}

struct TaskNoteRow_Previews: PreviewProvider {
    static var previews: some View {
        TaskNoteRow(ticketNote: TaskNote())
    }
}
