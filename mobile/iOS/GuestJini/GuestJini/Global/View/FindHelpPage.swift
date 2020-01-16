//
//  FindHelpPage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct FindHelpPage: View {
    @ObservedObject var viewRouter: ViewRouter
    @State var helpSearchText:String = ""
    @State var helpSearchCancel:Bool = false
    
    init(viewRouter:ViewRouter) {
        self.viewRouter = viewRouter
        self.helpSearchText = viewRouter.searchText;
    }
    
    var body: some View {
        GeometryReader { geometry in
            ZStack{
                VStack{
                    VStack{
                        HStack{
                            Button(action: {
                                self.viewRouter.currentPage = ViewRoutes.HOME_PAGE
                            }) {
                                GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                                
                            }.padding(.horizontal)
                            
                            GuestJiniTitleText(title: "FIND HELP")
                            Spacer()
                        }.padding()
                        VStack{
                            HStack {
                                HStack {
                                    TextField("search", text: self.$helpSearchText, onEditingChanged: { isEditing in
                                        self.helpSearchCancel = true
                                    }, onCommit: {
                                        print("onCommit")
                                    }).foregroundColor(.primary)

                                    Button(action: {
                                        self.helpSearchText = ""
                                    }) {
                                        Image(systemName: "xmark.circle.fill").opacity(self.helpSearchText == "" ? 0 : 1)
                                    }
                                    Button(action: {
                                       
                                    }) {
                                         Image(systemName: "magnifyingglass")
                                    }.padding(.leading)
                                   
                                    
                                }
                                .padding(EdgeInsets(top: 8, leading: 6, bottom: 8, trailing: 15))
                                .foregroundColor(.secondary)
                                .background(Color.white)
                                .cornerRadius(20.0)
                            }
                            .padding(.horizontal)
                            .navigationBarHidden(self.helpSearchCancel)
                            HStack{
                                Spacer()
                                GuestJiniSubAction(actionText: "Popular Searches", systemImage: "chevron.down")
                                .padding(.horizontal)
                            }
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
}

struct FindHelpPage_Previews: PreviewProvider {
    static var previews: some View {
        FindHelpPage(viewRouter: ViewRouter())
    }
}
