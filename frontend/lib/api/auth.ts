import {GuestAuthResponse} from "@/types/user";

const API_BASE_URL = 'http://localhost:8080';

export const authApi = {
    createGuestUser: async (): Promise<GuestAuthResponse> => {
        const response = await fetch(`${API_BASE_URL}/api/auth/guest`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
        });

        if (!response.ok) {
            throw new Error(`Failed to create guest user: ${response.statusText}`);
        }


        return await response.json();
    }
}