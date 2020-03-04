//
//  RentInvoiceDetailPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/03/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI
struct RentInvoiceDetailPage: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var getRentInvoice:GetRentInvoice
      
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.getRentInvoice = GetRentInvoice(viewRouter: viewRouter, ticketId: viewRouter.primaryKey)
        UITableView.appearance().separatorStyle = .none
    }
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    HStack{
                        Button(action: {
                            self.viewRouter.currentPage = ViewRoutes.RENT_INVOICE_LIST_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        GuestJiniTitleText(title: "RENT INVOICE")
                        Spacer()
                    }.padding()
                    VStack{
                        if(self.getRentInvoice.fetchComplete == true){
                            VStack{
                                VStack{
                                    HStack{
                                        Text(self.getRentInvoice.accountTicket.ticketDate!.convetToDateFromISOLUTC())
                                            .font(Fonts.RobotRegular)
                                            .bold()
                                            .foregroundColor(Color("greyishBrownFour"))
                                        Spacer()
                                    }
                                    HStack{
                                        Text(self.getRentInvoice.accountTicket.ticketNumber!)
                                            .font(Fonts.RobotRegularSmallText)
                                            .foregroundColor(Color("brownishGrey"))
                                        Spacer()
                                    }
                                    
                                    HStack{
                                        Text(self.getRentInvoice.accountTicket.ticketNarration!)
                                            .font(Fonts.RobotRegularSmallText)
                                            .foregroundColor(Color("brownishGrey"))
                                            .multilineTextAlignment(.leading)
                                            .lineLimit(500)
                                        Spacer()
                                    }
                                    
                                    HStack{
                                        Text("Total Amount  Rs. \(String(format:"%.2f", self.getRentInvoice.accountTicket.netTotal!))")
                                            .font(Fonts.RobotRegular)
                                            .bold()
                                            .foregroundColor(Color("greyishBrownFour"))
                                        Spacer()
                                    }
                                }.padding()
                            }.overlay(RoundedRectangle(cornerRadius: 8).stroke(Color("brownishGrey"),lineWidth: 1))
                            
                            if(self.getRentInvoice.accountTicket.accountTicketItems!.count > 0){
                                VStack{
                                    VStack{
                                        VStack{
                                            HStack{
                                                Text("More Details")
                                                    .font(Fonts.RobotRegular)
                                                    .bold()
                                                    .foregroundColor(Color("greyishBrownFour"))
                                                Spacer()
                                            }
                                            ForEach(self.getRentInvoice.accountTicket.accountTicketItems!){ accountTicketItem in
                                                 HStack{
                                                    Text(accountTicketItem.itemNarration!)
                                                        .font(Fonts.RobotRegularSmallText)
                                                        .foregroundColor(Color("brownishGrey"))
                                                    Spacer()
                                                    Text("Rs. \(String(format:"%.2f", accountTicketItem.itemTotal!))")
                                                        .font(Fonts.RobotRegularSmallText)
                                                        .foregroundColor(Color("brownishGrey"))
                                                }.padding()
                                            }
                                        }
                                    }.padding()
                                }.overlay(RoundedRectangle(cornerRadius: 8).stroke(Color("brownishGrey"),lineWidth: 1))
                                    .padding(.top, 50)
                            }
                            Button(action:{
                                self.viewRouter.currentPage = ViewRoutes.PAYTM_PAYMENT_SCREEN
                                self.viewRouter.transactionAMount =  self.getRentInvoice.accountTicket.netTotal!
                            }){
                                GuestJiniRectangularButtonText(buttonText: "PAY NOW")
                            }.padding(.top, 50)
                            
                        }
                    }.padding()
                    
                    
                    
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

