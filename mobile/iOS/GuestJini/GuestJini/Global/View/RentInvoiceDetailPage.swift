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
                            self.viewRouter.currentPage = ViewRoutes.ACCOUNTS_LANDING_PAGE
                        }) {
                            GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                            
                        }.padding(.horizontal)
                        GuestJiniTitleText(title: "RENT INVOICE")
                        Spacer()
                        ZStack{
                            VStack{
                                Text("Hello")
                            }
                        }
                    }.padding()
                    VStack{
                        VStack{
                            HStack{
                                Text("05 Jul 2019")
                                Spacer()
                            }
                            HStack{
                                Text("Ticket Number")
                                Spacer()
                            }
                            
                            HStack{
                                Text("Narration")
                                Spacer()
                            }
                            
                            HStack{
                                Text("Total AMount")
                                Spacer()
                            }
                        }
                    }
                    
                    VStack{
                        HStack{
                            Text("More Details")
                            Spacer()
                        }
//                        VStack{
//                            ForEach(
//                        }
                    }
                    
                    
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}
