import LoginForm from "@/app/login/LoginForm";

export default function Login() {
    return (
        <div className="flex flex-col flex-1 items-center justify-center bg-background text-foreground">
            <main className="flex flex-1 w-full max-w-3xl flex-col items-center justify-between py-32 px-16  ">
                <div className="flex flex-col w-full items-center justify-center">
                    <LoginForm/>
                </div>
            </main>
        </div>
    )
}