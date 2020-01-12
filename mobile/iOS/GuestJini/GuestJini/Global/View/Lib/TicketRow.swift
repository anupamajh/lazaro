//
//  TicketRow.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct TicketRow: View {
    @State var ticket:Ticket
    var body: some View {
        VStack{
            HStack{
                if(ticket.ticketStatus == 3){
                    Text("OPEN")
                        .padding(.all,5)
                        .background(Color("coral"))
                        .foregroundColor(Color.white)
                        .font(Fonts.RobotRegularText)
                        .cornerRadius(5)
                }else if(ticket.ticketStatus == 2){
                    Text("STARTED")
                        .padding(.all,5)
                        .background(Color("squash"))
                        .foregroundColor(Color.white)
                        .font(Fonts.RobotRegularText)
                        .cornerRadius(5)
                }else if(ticket.ticketStatus == 1){
                    Text("CLOSED")
                        .padding(.all,5)
                        .background(Color("blueyGrey"))
                        .foregroundColor(Color.white)
                        .font(Fonts.RobotRegularText)
                        .cornerRadius(5)
                }else{
                    Text("NEW")
                        .padding(.all,5)
                        .background(Color("coral"))
                        .foregroundColor(Color.white)
                        .font(Fonts.RobotRegularText)
                        .cornerRadius(5)
                }
                
                Spacer()
            }
            HStack{
                Text(ticket.creationTime!.convetToDate())
                    .font(Fonts.RobotRegularSmallText)
                    .foregroundColor(Color("brownishGrey"))
                Spacer()
            }.padding(.top, 15)
            HStack{
                Text(ticket.ticketTitle!)
                    .font(Fonts.RobotRegular)
                    .foregroundColor(Color("greyishBrown"))
                Spacer()
            }.padding(.bottom, 10)
            Divider()
            
            HStack{
                Text("Ticket #")
                    .font(Fonts.RobotRegularSmallText)
                    .foregroundColor(Color("brownishGrey"))
                Spacer()
            }.padding(.bottom, 10)
            HStack{
                Text(ticket.ticketNo!)
                    .font(Fonts.RobotRegular)
                    .foregroundColor(Color("greyishBrown"))
                Spacer()
            }.padding(.bottom, 10)
            
        }.padding()
            .background(Color("whiteThree"))
            .shadow(radius: 10)
            .cornerRadius(5)
            .overlay(
                RoundedRectangle(cornerRadius:5)
                    .stroke(Color("veryLightPink"), lineWidth: 1)
        )
    }
}

struct TicketRow_Previews: PreviewProvider {
    var ticket:Ticket = Ticket()
    
    
    static var previews: some View {
        TicketRow(ticket: Ticket())
    }
}
