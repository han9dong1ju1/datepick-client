import SwiftUI

struct ContentView: View {

    @Environment(\.colorScheme) var colorScheme

    var body: some View {
        TabView {

            HomeScreenView()
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("Home")
                }

            MapScreenView()
                .tabItem {
                    Image(systemName: "map.fill")
                    Text("Map")
                }

            MyDateScreenView()
                .tabItem {
                    Image(systemName: "heart.fill")
                    Text("My Date")
                }

            ProfileScreenView()
                .tabItem {
                    Image(systemName: "person.fill")
                    Text("Profile")
                }

        }
                .accentColor(colorScheme == .dark ? .white : .black)
                .eraseToAnyView()
    }

    #if DEBUG
    @ObservedObject var iO = injectionObserver
    #endif
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
