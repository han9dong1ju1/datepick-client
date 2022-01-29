import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        startKoin()
		#if DEBUG
		Bundle(path: "/Applications/InjectionIII.app/Contents/Resources/iOSInjection.bundle")?.load()
//for tvOS:
		Bundle(path: "/Applications/InjectionIII.app/Contents/Resources/tvOSInjection.bundle")?.load()
//Or for macOS:
		Bundle(path: "/Applications/InjectionIII.app/Contents/Resources/macOSInjection.bundle")?.load()
		#endif
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
