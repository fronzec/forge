import aiofiles
from fastapi import FastAPI
from pydantic import BaseModel


app = FastAPI()


class DnfTransaction(BaseModel):
    id: int
    command: str
    date: str


async def read_history():
    transactions = []
    async with aiofiles.open("history.txt") as history:
        async for line in history:
            transaction_id, command, date, *_ = line.split("|")
            transactions.append(
                DnfTransaction(
                    id=transaction_id.strip(),
                    command=command.strip(),
                    date=date.strip(),
                )
            )
    return transactions


@app.get("/")
async def read_root():
    return await read_history()
