'use client'

import {Card, CardContent, CardHeader} from "@/components/ui/card";
import {Field, FieldGroup, FieldLabel, FieldSet} from "@/components/ui/field";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {Separator} from "@/components/ui/separator";
import {authApi} from "@/lib/api/auth";
import {useState} from "react";

export default function LoginForm() {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleGuestLogin = async () => {
        setIsLoading(true);
        setError(null);

        try {
            const guestUser = await authApi.createGuestUser();
        } catch (err) {
            setError(err instanceof Error ? err.message : 'Failed to create guest user');
            console.error('Error creating guest user:', err);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <Card className="w-full max-w-sm">
            <CardHeader>Login to your account</CardHeader>
            <CardContent>
                <form action="">
                    <FieldSet>
                        <FieldGroup>
                            <Field>
                                <FieldLabel htmlFor="username">Username</FieldLabel>
                                <Input id="username"/>
                            </Field>
                            <Field>
                                <FieldLabel htmlFor="password">
                                    Password
                                    <a
                                        href="#"
                                        className="ml-auto inline-block text-sm underline-offset-4 hover:underline"
                                    >
                                        Forgot your password?
                                    </a>
                                </FieldLabel>
                                <Input type="password" id="password"/>
                            </Field>
                            <Field>
                                <Button type="submit">Login</Button>
                            </Field>
                        </FieldGroup>
                    </FieldSet>
                </form>
                <Separator className="my-4"/>
                <div className="flex w-full max-w-sm justify-center">
                    <Button className="max-w-sm w-full" variant="outline" onClick={handleGuestLogin}>Login as a guest</Button>
                </div>
            </CardContent>
        </Card>
    )
}